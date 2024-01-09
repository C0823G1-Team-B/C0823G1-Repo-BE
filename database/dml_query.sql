insert into accounts(username,password)
values("admin","$2a$12$SETjCyHrXoxDIINafJBAouzXeAlDV5LQImYcbXedsohp40AjGgWuS");

insert into roles(name)
values("ROLE_ADMIN");

insert into user_roles(user_id,role_id)
values(1,1);

insert into driver(address,identity,name,phone_number)
values("Huế","046203423211","Nguyễn Phong Long","0915399153"),
("Quảng Nam","042203623211","Nguyễn Văn Long","0915329153"),
("Huế","046203016321","Nguyễn Anh Dũng","0915399323"),
("Đồng Hới","043203023271","Trần Gia Kiệt","0915219153"),
("Đà Nẵng","045298309203","Phan Văn Toại","0915769153"),
("Huế","046203012321","Nguyễn Anh Tuấn","0915393253"),
("Đà Nẵng","046203063711","Lê Văn Độ","0915769153"),
("Huế","046203021511","Nguyễn Trần Trung Quân","0915394353"),
("Quảng Nam","046203035611","Nguyễn Sơn Tùng","0915332153"),
("Huế","046203231211","Nguyễn Nguyễn","0915363453");

insert into customer(name,email,phone_number)
values("Abc","Abc@gmail.com",0123123123),
("Bảo lê","levannhatbao29@gmail.com",0912121223),
("Phương TD","ptd@gmail.com",0923232323),
("Vi","tieuvi200904@gmail.com",0921575817),
("Vi trần","latiah343@gmail.com",0921920904);

insert into car(license_plates,total_seats)
values("AB-0002",41),
("BC-1234",41),
("CD-0234",41),
("AX-1123",41),
("DE-9823",41),
("DN-7654",41),
("KM-1920",41);

insert into car_route(starting_point,ending_point)
values("Thành phố Hà Nội","Tỉnh Hà Giang"),
("Thành phố Hà Nội","Thành phố Đà Nẵng"),
("Tỉnh Hải Dương","Thành phố Hà Nội"),
("Tỉnh Thừa Thiên Huế","Thành phố Đà Nẵng"),
("Thành phố Đà Nẵng","Tỉnh Thừa Thiên Huế"),
("Tỉnh Quảng Nam","Thành phố Đà Nẵng"),
("Thành phố Đà Nẵng","Thành phố Hồ Chí Minh");

insert into car_route_individual(start_time,end_time,price,car_id,car_route_id,driver_id)
values("2024-01-10 14:00","2024-01-11 14:10",350000,1,6,1),
("2024-01-10 05:20","2024-01-10 07:30",250000,1,3,2),
("2024-01-11 14:20","2024-01-11 18:30",250000,2,3,3),
("2024-01-11 09:00","2024-01-11 11:30",250000,1,2,1),
("2024-01-12 13:00","2024-01-13 08:30",500000,5,1,4),
("2024-09-20 20:00","2024-09-20 22:45",650000,4,5,4);



