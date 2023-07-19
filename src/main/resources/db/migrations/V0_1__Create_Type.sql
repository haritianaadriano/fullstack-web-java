do
$$
begin
        if not exists(select from pg_type where typname = 'csp') then
        create type csp as enum ('M1', 'M2', 'OS1', 'OS2', 'OS3', 'OP1');
end if;
end
$$;

do
$$
begin
        if not exists(select from pg_type where typname = 'sexe') then
        create type sexe as enum ('F', 'H');
end if;
end
$$;

