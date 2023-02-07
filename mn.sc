import('_util', ...system_variable_get('util_list'));

__config() -> {
	'scope' -> 'player',
	'stay_loaded' -> true,

	'commands' -> {
		'start'	-> _()	-> MN_Start(player()),
		'vote'	-> _()	-> MN_Vote(player()),
		'skip'	-> _()	-> MN_Skip(player()),
	},

	'arguments' -> {

	}
};





MN_Start(player) -> (
	if(player ~ 'uuid' != '6afdf1d2-87cf-4e63-8556-53d63687b52b', (
		return(false);
	));

	for(scoreboard('list'), scoreboard('list', _, null));

	for(shuffle(player('all')), (
		p = _;
		scoreboard('list', p, _i);
	));
);