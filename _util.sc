__config() -> {
	'stay_loaded' -> true,
	'scope' -> 'global'
};





//=====================
//= File Manipulation =
//=====================

thru_dir(path, file_type) -> (
	files = [];
	for(list_files(path, file_type), (
		files += _;
	));

	for(list_files(path, 'shared_folder'), (
		put(files, null, thru_dir(_, file_type), 'extend');
	));
	files;
);





//==========
//= Random =
//==========

randint(min,max) -> (
	floor( rand(max - min + 1) + min );
);

randstring(length) -> (
	string_array = [];
	loop(
		length,
		string_array += global_data:'alphabet':randint(0, 25);
	);
	join('', string_array);
);

randsentence(length, min, max) -> (
	sentence = [];
	loop(
		length,
		sentence += randstring(randint(min, max));
	);
	join(' ', sentence);
);

shuffle(list) -> (
	c_for(i = length(list) - 1, i > 0, i = i - 1, (
		j = floor(rand(i + 1));
		temp = list:i;
		list:i = list:j;
		list:j = temp;
	));
	list;
);





//=========
//= Lists =
//=========

force_list(input) -> (
	if(
		type(input) != 'list', [input],
		input
	);
);





//===================
//= Physics Library =
//===================

//* Returns the relative coordinates needed to move 1 block forward
vector(yaw, pitch) -> (
	[
		-sin(yaw) * cos(pitch),
		-sin(pitch),
		cos(yaw) * cos(pitch)
	]
);

//* Calculates the yaw and pitch needed for someone standing at pos1 to face pos2
direction(pos1, pos2) -> (
	[dx, dy, dz] = pos2 - pos1;
	[
		atan2(dz, dx) - 90, // yaw
		-atan2(dy, sqrt(dx^2 + dz^2)) // pitch
	]
);

//* Can be used to get the distance between two objects by doing `pythagorean([dx, dy, dz])`
pythagorean(l) -> (
	sqrt(reduce(l, _^2 + _a, 0));
);

//* Returns the two corners that make up the entity's hitbox
//* Can be used to test whether a position is in an entity's hitbox
hitbox(entity) -> (
	[
		pos(entity) - [entity ~ 'width' / 2, 0, entity ~ 'width' / 2],
		pos(entity) + [entity ~ 'width' / 2, entity ~ 'height', entity ~ 'width' / 2]
	]
);





//========
//= Text =
//========

//* Counts how many decimals a number has
//! Only accepts numbers
decimals(value) -> (
	if(floor(value) == value, 0, length(split('\\.', value):1));
);



//* Converts a number into a string and rounds it x amount of digits and adds trailing zeros to maintain a constant length
to_fixed(value, digits) -> (
	rounded_value = round(value * 10^digits) / 10^digits;
	decimals_missing = digits - decimals(rounded_value);
	while(
		decimals_missing > 0,
		digits, (
			if( decimals_missing == digits, rounded_value += '.');
			rounded_value += '0';
			decimals_missing = decimals_missing - 1;
		)
	);
	rounded_value;
);



roundto(value, digits) -> (
	x = 10^digits;
	round(value * x) / x;
);



//* Converts value (number) to a string and prepends zeros until it reaches a specified length (ignores decimals)
pad(value, length) -> (
	loop(length - length(value ~ '[^.]*'), (
		value = '0' + value
	));
	value;
);



//* Adds a thousandths comma delimiter
//! Does not work with floats
commify(number) -> (
	replace(number, '\\B(?=(\\d{3})+(?!\\d))', ',');
);



//* Returns a string concatenation of the inputted number and its ordinal
//? I.e., 1st, 2nd, 3rd, 4th...
//! Processes floats as integers
ordinal(n) -> (
	if(
		(n % 100) >= 11 && (n % 100) <= 13, n + 'th',
		n + ['th', 'st', 'nd', 'rd', 'th']:(min(n % 10, 4))
	);
);





//===============
//= Scoreboards =
//===============

scoreboard_addvalue(objective, scoreholder, value) -> (
	scoreboard(objective, scoreholder, scoreboard(objective, scoreholder) + value)
);



add_scoreboard_tag(entity, tag) -> (
	run(str('tag %s add %s', entity ~ 'command_name', tag));
);



remove_scoreboard_tag(entity, tag) -> (
	run(str('tag %s remove %s', entity ~ 'command_name', tag));
);





//================
//= Global Lists =
//================

global_items = {};
for(
	thru_dir('items', 'shared_nbt'), (
		k = replace(_, '^items/');
		v = read_file(_, 'shared_nbt');
		global_items:k = [v:'Count', v:'id', v:'tag'];
	)
);











system_variable_set('util_list', filter(
	import('_util'),
	['__config', 'utils'] ~ _ == null
));