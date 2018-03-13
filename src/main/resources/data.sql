/**
 * CREATE Script for init of DB
 */

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08');

-- Create 1 Electric Cars
insert into car (car_id, date_created, deleted, license_plate, seat_count, engine_type, manufacturer, car_status) values (1, now(), false, 'HHK7431', 4, 'ELECTRIC', 'OPEL', 'UNMAP');

-- Create 1 Gas Cars
insert into car (car_id, date_created, deleted, license_plate, seat_count, engine_type, manufacturer, car_status) values (2, now(), false, 'BP26571', 5, 'GAS', 'AUDI', 'UNMAP');

-- Create 2 Mapped Cars
insert into car (car_id, date_created, deleted, license_plate, seat_count, engine_type, manufacturer, car_status) values (3, now(), false, 'BAE2549', 5, 'DIESEL', 'AUDI', 'MAP');

insert into car (car_id, date_created, deleted, license_plate, seat_count, engine_type, manufacturer, car_status) values (4, now(), false, 'WOBV6097', 5, 'GAS', 'PORSCHE', 'MAP');

-- Create 2 UnMapped Cars 

insert into car (car_id, date_created, deleted, license_plate, seat_count, engine_type, manufacturer, car_status) values (5, now(), false, 'Y127508', 6, 'ELECTRIC', 'FORD', 'UNMAP');

insert into car (car_id, date_created, deleted, license_plate, seat_count, engine_type, manufacturer, car_status) values (6, now(), false, 'WWGG544', 4, 'DIESEL', 'OPEL', 'UNMAP');