# data for business-history dao test
INSERT INTO sale_returns_sheet (id, sale_sheet_id, operator, raw_total_amount, discount, voucher_amount, final_amount, state, create_time)
VALUES ('XSTHD-20220709-00000', 'XSD-20220524-00000', 'sky', 4200000.00, 0.80, 200.00, 3359800.00, '待一级审批', '2022-07-09 10:06:36');

INSERT INTO sale_returns_sheet_content (id, sale_returns_sheet_id, pid, quantity, total_price, unit_price, remark)
VALUES (13, 'XSTHD-20220709-00000', '0000000000400000', 600, 2100000.00, 3500.00, '');
INSERT INTO sale_returns_sheet_content (id, sale_returns_sheet_id, pid, quantity, total_price, unit_price)
VALUES (14, 'XSTHD-20220709-00000', '0000000000400001', 600, 2100000.00, 3500.00);

UPDATE sale_returns_sheet SET state = '审批完成'
WHERE id = 'XSTHD-20220709-00001';