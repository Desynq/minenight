import('_util', ...system_variable_get('util_list'));

global_ = {
	'args' -> {}
};
global_:'args' = 

__config() -> {
	'scope' -> 'player',
	'stay_loaded' -> true,

	'commands' -> {
		'start'						-> _()			-> MN_Start(player()),
		'vote'						-> _()			-> MN_Vote(player()),
		'skip'						-> _()			-> MN_Skip(player()),
		'propose add <player>'		-> _(proposee)	-> MN_ProposeAdd(player(), proposee),
		'propose remove <player>'	-> _(proposee)	-> MN_ProposeRemove(player(), proposee),
		'propose clear'				-> _()			-> MN_ProposeClear(player()),
	},

	'arguments' -> {
		'player' -> {
			'type' -> 'term',
			'singe' -> true,
			'suggester' -> _(args) -> (
				print(player('all'), args);
				scoreboard('list');
			)
		}
	}
};



global_ = {
	'hacker_comp' -> {
		5 -> 2,
		6 -> 2,
		7 -> 3,
		8 -> 3,
	}
};










MN_Start(player) -> (
	players = player('all');

	//* Check if perms and player count are acceptable before continuing
	cancel = if(
		player ~ 'uuid' != '6afdf1d2-87cf-4e63-8556-53d63687b52b', (
			print(player('all'), format('r Only the server host can start a game.'));
			return();
		),
		length(players) < 5, (
			print(player('all'), format('r Cannot start a game with less than 5 players.'));
			return();
		),
		length(players) > 8, (
			print(player('all'), format('r Cannot start a game with more than 8 players.'));
			return();
	));

	//* Clear all scoreboards for the upcoming game.
	for(['list', 'data', 'team'], (
		scoreboard = _;
		for(scoreboard(scoreboard), (
			scoreboard(scoreboard, _, null);
		));
	));


	hacker_count = global_:'hacker_comp':length(players);



	for(shuffle(players), (
		scoreboard('list', _, _i);
		scoreboard('team', _, 0);
	));

	shuffled_list = shuffle(players);
	for(slice(shuffled_list, 0, hacker_count), (
		scoreboard('team', _, 1);
	));


	scoreboard('data', 'hacker_count', hacker_count);
	scoreboard('data', 'phase', 0);
	for(['time_left', 'time_max'], scoreboard('data', _, 20 * 20));
	scoreboard('data', 'started', 1);
	0;
);