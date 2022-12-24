INSERT INTO `right` (`id`, `name`) VALUES (1, 'create_new_user');
INSERT INTO `right` (`id`, `name`) VALUES (2, 'view_other_users');
INSERT INTO `right` (`id`, `name`) VALUES (3, 'edit_other_user');
INSERT INTO `right` (`id`, `name`) VALUES (4, 'delete_other_user');
INSERT INTO `right` (`id`, `name`) VALUES (5, 'force_logout_users');
INSERT INTO `right` (`id`, `name`) VALUES (6, 'create_global_feeds');
INSERT INTO `right` (`id`, `name`) VALUES (7, 'edit_global_feeds');
INSERT INTO `right` (`id`, `name`) VALUES (8, 'delete_global_feeds');
INSERT INTO `right` (`id`, `name`) VALUES (9, 'create_own_feeds');
INSERT INTO `right` (`id`, `name`) VALUES (10, 'edit_own_feeds');
INSERT INTO `right` (`id`, `name`) VALUES (11, 'delete_own_feeds');
INSERT INTO `right` (`id`, `name`) VALUES (12, 'view_all_feeds');
INSERT INTO `right` (`id`, `name`) VALUES (13, 'create_other_user_feeds');
INSERT INTO `right` (`id`, `name`) VALUES (14, 'edit_other_user_feeds');
INSERT INTO `right` (`id`, `name`) VALUES (15, 'delete_other_user_feeds');

INSERT INTO `role` (`id`, `name`) VALUES (1, 'admin');
INSERT INTO `role` (`id`, `name`) VALUES (2, 'user');

INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,1);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,2);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,3);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,4);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,5);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,6);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,7);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,8);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,9);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,10);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,11);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,12);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,13);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,14);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (1,15);

INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (2, 9);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (2, 10);
INSERT INTO `role_right` (`role_id`, `right_id`) VALUES (2, 11);

