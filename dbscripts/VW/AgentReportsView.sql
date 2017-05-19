CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `esoko`@`%` 
    SQL SECURITY DEFINER
VIEW `AgentReportsView` AS
    (select 
        `agent_details`.`detail_id` AS `detail_id`,
        `agent_details`.`network_id` AS `network_id`,
        `agent_details`.`user_id` AS `user_id`,
        `agent_details`.`application_id` AS `application_id`,
        `agent_details`.`target` AS `target`,
        `agent_details`.`effective_date` AS `effective_date`,
        `agent_details`.`bonus` AS `bonus`,
        `agent_details`.`incentive` AS `incentive`
    from
        `agent_details`
    where
        (`agent_details`.`record_status` = 'A')
    order by `application_id` , `effective_date` desc) union (select 
        `agent_details_history`.`detail_id` AS `detail_id`,
        `agent_details_history`.`network_id` AS `network_id`,
        `agent_details_history`.`user_id` AS `user_id`,
        `agent_details_history`.`application_id` AS `application_id`,
        `agent_details_history`.`target` AS `target`,
        `agent_details_history`.`effective_date` AS `effective_date`,
        `agent_details_history`.`bonus` AS `bonus`,
        `agent_details_history`.`incentive` AS `incentive`
    from
        `agent_details_history`)