__config() -> {
	'scope' -> 'global',
	'stay_loaded' -> true
};



__on_start() -> (
	0;
);








__on_tick() -> (
	if(scoreboard('data', 'started'), Main_GameStarted());
	0;
);







Main_GameStarted() -> (
	func = global_phase:scoreboard('data', 'phase');
	if(type(func) == 'function', call(func));
	0;
);









global_phase = [
_() -> ( // Talking Phase
	time_left = scoreboard('data', 'time_left');

	// Go to next phase if time has ran out, otherwise, deincrement time left by 1
	if(time_left <= 0, (
		for(['time_left', 'time_max'], scoreboard('data', _, 60 * 20));
		scoreboard('data', 'phase', 1);
	), (
		scoreboard('data', 'time_left', time_left - 1);
	));
	0;
),
_() -> ( // Proposition Phase
	time_left = scoreboard('data', 'time_left');
	proposer = first(scoreboard('list'), _ == scoreboard('data', 'proposer'));


	result = if(
		
		time_left <= 0, 'time_ran_out',
		'continue'
	);
),
];