# Focus Daily Statistic Update Trigger

## 설명
`focus_daily_statistic_update` 트리거는 `focus_session` 테이블에 레코드가 삽입될 때, 해당 레코드에 기반하여 `focus_daily_statistic` 테이블을 업데이트하거나 새로운 레코드를 삽입하는 트리거입니다. 이 트리거는 일별 통계 정보를 관리하여 사용자의 집중 시간을 효과적으로 추적합니다.

## 트리거 생성

```sql
CREATE TRIGGER focus_daily_statistic_update
AFTER INSERT ON focus_session
FOR EACH ROW 
BEGIN 
    DECLARE session_duration BIGINT;

    -- 세션의 지속 시간 계산
    SET session_duration = TIMESTAMPDIFF(SECOND, NEW.start_date_time, NEW.end_date_time);	

    -- 일별 통계 존재 여부 확인
    IF EXISTS (SELECT 1 FROM focus_daily_statistic WHERE member_id = NEW.member_id AND date = DATE(NEW.start_date_time)) THEN 
        -- 통계 업데이트
        UPDATE focus_daily_statistic 
        SET total_cumulative_time = total_cumulative_time + session_duration,
            max_duration_time = GREATEST(max_duration_time, session_duration),
            end_time = GREATEST(end_time, NEW.end_date_time)
        WHERE member_id = NEW.member_id AND date = DATE(NEW.start_date_time);
    ELSE
        -- 새로운 일별 통계 추가
        INSERT INTO focus_daily_statistic (member_id, date, start_time, end_time, total_cumulative_time, max_duration_time)
        VALUES (NEW.member_id, DATE(NEW.start_date_time), NEW.start_date_time, NEW.end_date_time, session_duration, session_duration);
    END IF;
END;