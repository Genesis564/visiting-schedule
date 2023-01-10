select s.visiting_week Неделя, uvd.username Преподаватель, uvr.username Проверяющий, ss.status Статус from schedule s
    join schedule_status ss on s.id = ss.schedule_id
    join usr uvd on uvd.id = s.visited_user_id
    join usr uvr on uvr.id = s.visiter_user_id