CREATE TRIGGER  trg_delete_passports
    BEFORE DELETE
    ON people FOR ROW
EXECUTE PROCEDURE delete_passports();