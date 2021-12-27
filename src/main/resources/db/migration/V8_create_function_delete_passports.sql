CREATE OR REPLACE FUNCTION delete_passports()
    RETURNS TRIGGER
AS $$
BEGIN
    DELETE FROM passports WHERE passports.people_id = OLD.id;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;
