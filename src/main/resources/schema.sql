drop table branches if exists;
drop table vaccines if exists;
drop table vaccines_branches if exists; 
drop table availability if exists;
drop table bookings if exists; 

create table branches (
   branch_id integer not null auto_increment,
	location varchar(255) not null,
	name varchar(255) not null,
	phone varchar(255) not null,
	primary key (branch_id),
	constraint unique_location unique (name,location)
); 
	
create table vaccines (
   vaccine_id integer not null auto_increment,
	name varchar(255) not null,
	type varchar(255),
	primary key (vaccine_id),
	constraint unique_vaccine unique (name,type)
);

create table vaccines_branches (
   vaccines_branches_id integer not null auto_increment,
   quantity integer,
   branch_branch_id integer not null,
   vaccine_vaccine_id integer not null,
   primary key (branch_branch_id, vaccine_vaccine_id),
   foreign key (branch_branch_id)  references branches,
   foreign key (vaccine_vaccine_id)  references vaccines
);

create table availability (
	availability_id integer not null auto_increment,
	branch_branch_id integer not null,
	dates date not null,
	slot varchar(255) not null,
	primary key (branch_branch_id, dates, slot),
	foreign key (branch_branch_id) references branches
);

create table bookings (
	booking_id integer not null auto_increment,
	branch_branch_id integer not null,
	vaccine_vaccine_id integer not null,
	payment_type varchar(255) not null,
	timestamp date not null,
	payment_method_id varchar(255),
	credit_card_number varchar(255),
	cvv varchar(255),
	amount double not null,
	expiry varchar(255),
	name varchar(255),
	email varchar(255),
	status varchar(255) not null default 'SCHEDULED',
	primary key (branch_branch_id, vaccine_vaccine_id,timestamp),
	foreign key (branch_branch_id)  references branches,
	foreign key (vaccine_vaccine_id)  references vaccines,
	constraint check_status check (status in ('SCHEDULED','COMPLETED'))
);