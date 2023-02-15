import('_util', ...system_variable_get('util_list'));

__config() -> {
	'scope' -> 'player',
	'stay_loaded' -> true
};



global_ = {
	'slot_maps' -> {},

	'screen' -> null,
};





_clear_cursor(screen) -> (
	schedule(0, _(outer(screen)) -> (
		cond1 = screen_property(screen, 'open');
		cond2 = inventory_get(screen, -1):2:'GUIOnly';

		if(cond1 && cond2, (
			inventory_set(screen, -1, 0);
		));
	));
);





//============
//= Main Menu
//============

global_:'slot_maps':'main_menu' = {
	'keys' -> [
	//	1   2   3   4   5   6   7   8   9
		00, 00, 00, 00, 00, 00, 00, 00, 00, // 1
		00, 00, 00, 00, 00, 00, 00, 00, 00, // 2
		00, 00, 00, 00, 00, 00, 00, 00, 00, // 3
		00, 00, 00, 00, 00, 00, 00, 00, 00, // 4
		00, 00, 00, 00, 00, 00, 00, 00, 00, // 5
		00, 00, 00, 00, 00, 00, 00, 00, 00, // 6
	],
	'values' -> [
		_(screen, player, action, data, slot) -> (
			inventory_set(screen, slot, 0);
		),
	],
};

GUI_MainMenu_CreateScreen(player) -> (
	global_:'screen' = create_screen(player,
			'generic_9x6',
			format('by MineNight™'),
			_(screen, player, action, data) -> GUI_MainMenu_Callback(screen, player, action, data));

	GUI_MainMenu_Callback(global_:'screen', player, 'init', null);
);

GUI_MainMenu_Callback(screen, player, action, data) -> (
	cursor = inventory_get(screen, -1);
	_clear_cursor(screen);

	slot_map = global_:'slot_maps':'main_menu';
	size = length(slot_map:'keys');


	c_for(slot = 0, slot < size, slot += 1, (
		func_key = slot_map:'keys':slot;
		func = slot_map:'values':func_key;

		if(type(func) == 'function', (
			call(func, screen, player, action, data, slot);
		));
	));

	'cancel';
);