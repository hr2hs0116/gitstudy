--1번
SELECT * FROM Orders WHERE CustomerID > 33 AND EmployeeID < 5;
--2번
SELECT * FROM Orders WHERE OrderID < 10000 OR ShipperID < 5;
--3번
SELECT * FROM OrderDetails WHERE ProductID > 14;
--4번
SELECT CustomerName, City FROM Customers WHERE CustomerID > 10 AND CustomerID <= 50;
--5번
SELECT * FROM Orders WHERE CustomerID >= 30 AND CustomerID <= 39;
--6번
SELECT ProductName, ProductID FROM Products WHERE ProductID % 2 = 0 AND SupplierID % 2 = 1;