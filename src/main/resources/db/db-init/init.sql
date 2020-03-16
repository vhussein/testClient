SELECT 'Update pcdbuser host';
UPDATE mysql.user SET HOST = '%' WHERE USER='pcdbuser';

SELECT 'Grant priveledge';
GRANT ALL PRIVILEGES ON *.* TO 'pcdbuser'@'%' WITH GRANT OPTION;

FLUSH PRIVILEGES;
