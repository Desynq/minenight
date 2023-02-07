__config() -> {
	'stay_loaded' -> true,
	'scope' -> 'global'
};

__on_start()->(
	print(player('all'), 'hello world');
);