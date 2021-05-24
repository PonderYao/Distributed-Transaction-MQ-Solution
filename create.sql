create table platoon(
  id INT PRIMARY KEY AUTO_INCREMENT,
  orderId VARCHAR(255),
  takeout_userId int
)

create table order_info(
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30),  
  order_money INT,
  orderId VARCHAR(255)
);
