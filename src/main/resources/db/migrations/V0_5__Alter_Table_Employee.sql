ALTER TABLE "employee"
    ADD CONSTRAINT fk_employee_profile FOREIGN KEY (profile_id) REFERENCES "profile"(id);

ALTER TABLE "employee"
    ADD CONSTRAINT fk_employee_cin FOREIGN KEY (cin_id) REFERENCES cin(id);