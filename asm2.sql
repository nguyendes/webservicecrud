CREATE TABLE san_pham (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ma_san_pham VARCHAR(255),
    ten_san_pham VARCHAR(255),
    trang_thai VARCHAR(50), -- hoặc INT nếu bạn dùng số để biểu diễn trạng thái
    ngay_tao DATETIME,
    ngay_sua DATETIME
);

CREATE TABLE mau_sac (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ma_mau VARCHAR(255),
    ten_mau VARCHAR(255),
    trang_thai VARCHAR(50), -- hoặc INT nếu bạn dùng số để biểu diễn trạng thái
    ngay_tao DATETIME,
    ngay_sua DATETIME
);

CREATE TABLE size (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ma_size VARCHAR(255),
    ten_size VARCHAR(255),
    trang_thai VARCHAR(50), -- hoặc INT nếu bạn dùng số để biểu diễn trạng thái
    ngay_tao DATETIME,
    ngay_sua DATETIME
);

CREATE TABLE danh_muc (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ma_danh_muc VARCHAR(255),
    ten_danh_muc VARCHAR(255),
    trang_thai VARCHAR(50), -- hoặc INT nếu bạn dùng số để biểu diễn trạng thái
    ngay_tao DATETIME,
    ngay_sua DATETIME
);

CREATE TABLE khach_hang (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ho_ten NVARCHAR(255),
    dia_chi NVARCHAR(255),
    sdt VARCHAR(20),
    trang_thai VARCHAR(50), -- hoặc INT nếu bạn dùng số để biểu diễn trạng thái
    ngay_tao DATETIME,
    ngay_sua DATETIME
);

CREATE TABLE ctsp (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_sp INT,
    id_mau_sac INT,
    id_size INT,
    gia_ban DECIMAL(18, 2),
    so_luong_ton INT,
    trang_thai VARCHAR(50), -- hoặc INT nếu bạn dùng số để biểu diễn trạng thái
    ngay_tao DATETIME,
    ngay_sua DATETIME,
    FOREIGN KEY (id_sp) REFERENCES san_pham(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_mau_sac) REFERENCES mau_sac(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_size) REFERENCES size(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE hoa_don (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_khach_hang INT,
    trang_thai VARCHAR(50), -- hoặc INT nếu bạn dùng số để biểu diễn trạng thái
    ngay_tao DATETIME,
    ngay_sua DATETIME,
    dia_chi NVARCHAR(255),
    so_dien_thoai VARCHAR(20),
    FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE hdct (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_hoa_don INT,
    id_ctsp INT,
    so_luong_mua INT,
    gia_ban DECIMAL(18, 2),
    tong_tien DECIMAL(18, 2),
    trang_thai VARCHAR(50), -- hoặc INT nếu bạn dùng số để biểu diễn trạng thái
    ngay_tao DATETIME,
    ngay_sua DATETIME,
    FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (id_ctsp) REFERENCES ctsp(id) ON UPDATE CASCADE ON DELETE CASCADE
);

ALTER TABLE san_pham
ADD id_danh_muc INT;

ALTER TABLE san_pham
ADD CONSTRAINT fk_san_pham_danh_muc
FOREIGN KEY (id_danh_muc) REFERENCES danh_muc(id) ON UPDATE CASCADE ON DELETE CASCADE;

-- Thêm dữ liệu mẫu cho bảng 'danh_muc'
INSERT INTO danh_muc (ma_danh_muc, ten_danh_muc, trang_thai, ngay_tao, ngay_sua)
VALUES
('DM01', 'Thời trang nam', 'Hoạt động', NOW(), NOW()),
('DM02', 'Thời trang nữ', 'Hoạt động', NOW(), NOW());

-- Thêm dữ liệu mẫu cho bảng 'mau_sac'
INSERT INTO mau_sac (ma_mau, ten_mau, trang_thai, ngay_tao, ngay_sua)
VALUES
('MS01', 'Đỏ', 'Hoạt động', NOW(), NOW()),
('MS02', 'Xanh', 'Hoạt động', NOW(), NOW());

-- Thêm dữ liệu mẫu cho bảng 'size'
INSERT INTO size (ma_size, ten_size, trang_thai, ngay_tao, ngay_sua)
VALUES
('S01', 'Small', 'Hoạt động', NOW(), NOW()),
('S02', 'Medium', 'Hoạt động', NOW(), NOW());

-- Thêm dữ liệu mẫu cho bảng 'san_pham'
INSERT INTO san_pham (ma_san_pham, ten_san_pham, trang_thai, ngay_tao, ngay_sua, id_danh_muc)
VALUES
('SP01', 'Áo thun nam', 'Hoạt động', NOW(), NOW(), 1),
('SP02', 'Áo sơ mi nữ', 'Hoạt động', NOW(), NOW(), 2);

-- Thêm dữ liệu mẫu cho bảng 'khach_hang'
INSERT INTO khach_hang (ho_ten, dia_chi, sdt, trang_thai, ngay_tao, ngay_sua)
VALUES
('Nguyễn Văn A', '123 Đường A, Quận 1, TP.HCM', '0909123456', 'Hoạt động', NOW(), NOW()),
('Trần Thị B', '456 Đường B, Quận 2, TP.HCM', '0918123456', 'Hoạt động', NOW(), NOW());

-- Thêm dữ liệu mẫu cho bảng 'ctsp' (Chi tiết sản phẩm)
INSERT INTO ctsp (id_sp, id_mau_sac, id_size, gia_ban, so_luong_ton, trang_thai, ngay_tao, ngay_sua)
VALUES
(1, 1, 1, 150000, 50, 'Hoạt động', NOW(), NOW()),
(2, 2, 2, 200000, 30, 'Hoạt động', NOW(), NOW());

-- Thêm dữ liệu mẫu cho bảng 'hoa_don'
INSERT INTO hoa_don (id_khach_hang, trang_thai, ngay_tao, ngay_sua, dia_chi, so_dien_thoai)
VALUES
(1, 'Đang xử lý', NOW(), NOW(), '123 Đường A, Quận 1, TP.HCM', '0909123456'),
(2, 'Đang xử lý', NOW(), NOW(), '456 Đường B, Quận 2, TP.HCM', '0918123456');

-- Thêm dữ liệu mẫu cho bảng 'hdct' (Chi tiết hóa đơn)
INSERT INTO hdct (id_hoa_don, id_ctsp, so_luong_mua, gia_ban, tong_tien, trang_thai, ngay_tao, ngay_sua)
VALUES
(1, 1, 2, 150000, 300000, 'Hoàn thành', NOW(), NOW()),
(2, 2, 1, 200000, 200000, 'Hoàn thành', NOW(), NOW());
