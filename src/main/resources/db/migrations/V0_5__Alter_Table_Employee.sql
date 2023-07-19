ALTER TABLE "employee"
    ADD CONSTRAINT fk_employee_profile FOREIGN KEY (profile_id) REFERENCES "profile"(id);

ALTER TABLE "employee"
    ADD CONSTRAINT fk_employee_cin FOREIGN KEY (CIN_id) REFERENCES "CIN"(id);