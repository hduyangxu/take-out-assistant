/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/3 20:45:48                            */
/*==============================================================*/


drop table if exists tbl_address;

drop table if exists tbl_discountCoupon;

drop table if exists tbl_discountCouponRequest;

drop table if exists tbl_fullReduction;

drop table if exists tbl_merchant;

drop table if exists tbl_orderDetail;

drop table if exists tbl_productDetails;

drop table if exists tbl_productEvaluate;

drop table if exists tbl_productOrder;

drop table if exists tbl_productType;

drop table if exists tbl_rider;

drop table if exists tbl_riderAccount;

drop table if exists tbl_systemUser;

drop table if exists tbl_user;

drop table if exists tbl_userDiscountCoupon;

/*==============================================================*/
/* Table: tbl_address                                           */
/*==============================================================*/
create table tbl_address
(
   address_id           int not null,
   user_id              int,
   address_province     varchar(80),
   address_city         varchar(80),
   address_zone         varchar(80),
   address_detail       varchar(80),
   address_people       varchar(80),
   address_tel          varchar(80),
   primary key (address_id)
);

/*==============================================================*/
/* Table: tbl_discountCoupon                                    */
/*==============================================================*/
create table tbl_discountCoupon
(
   discountCoupon       int not null,
   merchant_id          int,
   discountCoupon_money float,
   discountCoupon_startDate datetime,
   discountCoupon_endDate datetime,
   discountCoupon_request int,
   primary key (discountCoupon)
);

/*==============================================================*/
/* Table: tbl_discountCouponRequest                             */
/*==============================================================*/
create table tbl_discountCouponRequest
(
   user_id              int not null,
   merchant_id          int not null,
   discountCoupon_id    int not null,
   discountCoupon_already int not null,
   discountCoupon_request int not null,
   primary key (user_id, merchant_id)
);

/*==============================================================*/
/* Table: tbl_fullReduction                                     */
/*==============================================================*/
create table tbl_fullReduction
(
   fullReduction_id     int not null,
   merchant_id          int,
   fullReduction_request float,
   fullReduction_money  float,
   fullReduction_isConflict bool,
   primary key (fullReduction_id)
);

/*==============================================================*/
/* Table: tbl_merchant                                          */
/*==============================================================*/
create table tbl_merchant
(
   merchant_id          int not null,
   merchant_name        varchar(80),
   merchant_starRated   int,
   merchant_avgConsumption float,
   merchant_totalSales  int,
   primary key (merchant_id)
);

/*==============================================================*/
/* Table: tbl_orderDetail                                       */
/*==============================================================*/
create table tbl_orderDetail
(
   product_id           int not null,
   merchant_id          int not null,
   order_id             int not null,
   product_quantity     int,
   product_sumPrice     float,
   product_discountPrice float,
   primary key (product_id, merchant_id, order_id)
);

/*==============================================================*/
/* Table: tbl_productDetails                                    */
/*==============================================================*/
create table tbl_productDetails
(
   product_id           int not null,
   merchant_id          int not null,
   type_id              int,
   product_name         varchar(80),
   product_price        float,
   product_discountPrice float,
   primary key (product_id, merchant_id)
);

/*==============================================================*/
/* Table: tbl_productEvaluate                                   */
/*==============================================================*/
create table tbl_productEvaluate
(
   product_id           int not null,
   merchant_id          int not null,
   user_id              int not null,
   evaluate_content     varchar(80),
   evaluate_date        datetime,
   evaluate_starRated   int,
   evaluate_photo       longblob,
   primary key (product_id, merchant_id, user_id)
);

/*==============================================================*/
/* Table: tbl_productOrder                                      */
/*==============================================================*/
create table tbl_productOrder
(
   order_id             int not null,
   address_id           int,
   merchant_id          int,
   rider_id             int,
   discountCoupon_id    int,
   fullReduction_id     int,
   originalPrice        float,
   finalPrice           float,
   order_startDate      datetime,
   order_requestDate    datetime,
   order_realDate       datetime,
   order_addressId      int,
   order_state          varchar(80),
   primary key (order_id)
);

/*==============================================================*/
/* Table: tbl_productType                                       */
/*==============================================================*/
create table tbl_productType
(
   type_id              int not null,
   type_name            varchar(80),
   type_quantity        int,
   primary key (type_id)
);

/*==============================================================*/
/* Table: tbl_rider                                             */
/*==============================================================*/
create table tbl_rider
(
   rider_id             int not null,
   rider_name           varchar(80),
   rider_joinDate       datetime,
   rider_identification varchar(80),
   primary key (rider_id)
);

/*==============================================================*/
/* Table: tbl_riderAccount                                      */
/*==============================================================*/
create table tbl_riderAccount
(
   account_id           int not null,
   rider_id             int not null,
   order_id             int not null,
   order_userEvaluate   varchar(80),
   unitPrice            float,
   primary key (account_id, rider_id)
);

/*==============================================================*/
/* Table: tbl_systemUser                                        */
/*==============================================================*/
create table tbl_systemUser
(
   systemUser_id        int,
   systemUser_name      varchar(80),
   systemUser_password  varchar(80)
);

/*==============================================================*/
/* Table: tbl_user                                              */
/*==============================================================*/
create table tbl_user
(
   user_id              int not null,
   user_name            varchar(80),
   user_sex             int,
   user_password        varchar(80),
   user_telNumber       varchar(80),
   user_email           varchar(80),
   user_city            varchar(80),
   user_regDate         datetime,
   user_isVIP           bool,
   user_vipEndDate      datetime,
   primary key (user_id)
);

/*==============================================================*/
/* Table: tbl_userDiscountCoupon                                */
/*==============================================================*/
create table tbl_userDiscountCoupon
(
   user_id              int not null,
   discountCoupon_id    int not null,
   dicountCoupon_count  int not null,
   primary key (user_id, discountCoupon_id)
);

alter table tbl_address add constraint FK_拥有9 foreign key (user_id)
      references tbl_user (user_id) on delete restrict on update restrict;

alter table tbl_discountCoupon add constraint FK_拥有 foreign key (merchant_id)
      references tbl_merchant (merchant_id) on delete restrict on update restrict;

alter table tbl_discountCouponRequest add constraint FK_tbl_discountCouponRequest foreign key (user_id)
      references tbl_user (user_id) on delete restrict on update restrict;

alter table tbl_discountCouponRequest add constraint FK_tbl_discountCouponRequest2 foreign key (merchant_id)
      references tbl_merchant (merchant_id) on delete restrict on update restrict;

alter table tbl_fullReduction add constraint FK_拥有1 foreign key (merchant_id)
      references tbl_merchant (merchant_id) on delete restrict on update restrict;

alter table tbl_orderDetail add constraint FK_tbl_orderDetail foreign key (product_id, merchant_id)
      references tbl_productDetails (product_id, merchant_id) on delete restrict on update restrict;

alter table tbl_orderDetail add constraint FK_tbl_orderDetail2 foreign key (order_id)
      references tbl_productOrder (order_id) on delete restrict on update restrict;

alter table tbl_productDetails add constraint FK_属于 foreign key (type_id)
      references tbl_productType (type_id) on delete restrict on update restrict;

alter table tbl_productDetails add constraint FK_拥有4 foreign key (merchant_id)
      references tbl_merchant (merchant_id) on delete restrict on update restrict;

alter table tbl_productEvaluate add constraint FK_tbl_productEvaluate foreign key (product_id, merchant_id)
      references tbl_productDetails (product_id, merchant_id) on delete restrict on update restrict;

alter table tbl_productEvaluate add constraint FK_tbl_productEvaluate2 foreign key (user_id)
      references tbl_user (user_id) on delete restrict on update restrict;

alter table tbl_productOrder add constraint FK_使用1 foreign key (discountCoupon_id)
      references tbl_discountCoupon (discountCoupon) on delete restrict on update restrict;

alter table tbl_productOrder add constraint FK_可以用在 foreign key (fullReduction_id)
      references tbl_fullReduction (fullReduction_id) on delete restrict on update restrict;

alter table tbl_productOrder add constraint FK_拥有12 foreign key (merchant_id)
      references tbl_merchant (merchant_id) on delete restrict on update restrict;

alter table tbl_productOrder add constraint FK_拥有123 foreign key (rider_id)
      references tbl_rider (rider_id) on delete restrict on update restrict;

alter table tbl_productOrder add constraint FK_确定 foreign key (address_id)
      references tbl_address (address_id) on delete restrict on update restrict;

alter table tbl_riderAccount add constraint FK_拥有6 foreign key (rider_id)
      references tbl_rider (rider_id) on delete restrict on update restrict;

alter table tbl_riderAccount add constraint FK_拥有8 foreign key (order_id)
      references tbl_productOrder (order_id) on delete restrict on update restrict;

alter table tbl_userDiscountCoupon add constraint FK_tbl_userDiscountCoupon foreign key (user_id)
      references tbl_user (user_id) on delete restrict on update restrict;

alter table tbl_userDiscountCoupon add constraint FK_tbl_userDiscountCoupon2 foreign key (discountCoupon_id)
      references tbl_discountCoupon (discountCoupon) on delete restrict on update restrict;

