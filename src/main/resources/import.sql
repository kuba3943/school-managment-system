insert into student values (1, '2010-03-02', 'Jakub', '$2y$12$jdEzyzF4KqMnmLqUJP8M.estimnM/k1QmOupzjTfC5AEq52RwNPoK', 'Jeden', 'STUDENT', 'kuba' );
insert into student values (2, '2010-04-02', 'Tomasz', '$2y$12$klr8fuVUwQlC6zWm7rqrPufoDzRnO9cXHjOl3YwSe3rZRiKnxT8NC', 'Drugi', 'STUDENT', 'tomek' );
insert into student values (3, '2010-05-02', 'Michał', '$2y$12$H6wu/2A5jlCwiNeGavFWkup9CZ1K9jRUJzYF/D6p8OMY9AN2G.qZO', 'Trzeci', 'STUDENT', 'mich' );
insert into student values (4, '2010-06-02', 'Jan', '$2y$12$SMfXePjRjVCiXx8DDnnt6OsZ3TZIUea52zYplAw3teyu4N9717XbK', 'Czwarty', 'STUDENT', 'jan' );
insert into student values (5, '2009-03-02', 'Anna', 'user4', 'Piata', 'STUDENT', 'ann' );
insert into student values (6, '2009-03-02', 'Katarzyna', 'user5', 'Szósta', 'STUDENT', 'kate' );
# insert into student values (7, '2009-03-02', 'Maria', 'user6', 'Siódmy', 'STUDENT', 'mary' );
# insert into student values (8, '2009-03-02', 'Jakub', 'user7', 'Ósmy', 'STUDENT', 'kuba2' );

insert into teacher values (1, 'Piotr', '$2y$12$zLOPfoELZXudd/ncfpd0Huhx.LudehKK9N/8JNc0JAGSNALHr6op.', 'Pierwszy', 'TEACHER', 'pete');

insert into sclass values (1, '1A');
insert into sclass values (2, '2A');

insert into sclass_student_list values (1, 1);
insert into sclass_student_list values (1, 2);
insert into sclass_student_list values (1, 3);
insert into sclass_student_list values (1, 4);

insert into school_subject values (1, 'Math-1A');
insert into school_subject values (2, 'English-1A');

insert into sclass_subjects values (1,1);
insert into sclass_subjects values (1,2);

insert into teacher_subject_list values (1, 1);

insert into point values (1, 1);
insert into point values (2, 2);
insert into point values (3, 3);
insert into point values (4, 4);
insert into point values (5, 5);

insert into grades values (1);
insert into grades values (2);
insert into grades values (3);


insert into grades_points values (1, 1);
insert into grades_points values (1, 2);
insert into grades_points values (1, 3);
insert into grades_points values (1, 4);
insert into grades_points values (1, 5);

insert into grades_points values (2, 3);
insert into grades_points values (2, 4);
insert into grades_points values (2, 5);

insert into grades_points values (3, 3);

insert into student_grades values (1, 1);
insert into student_grades values (1, 2);
insert into student_grades values (2, 3);

insert into school_subject_grades values (1, 1);
insert into school_subject_grades values (1, 3);
insert into school_subject_grades values (2, 2);




