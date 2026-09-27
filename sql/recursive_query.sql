SELECT DISTINCT  nd_id, next, parent, leval FROM (
WITH RECURSIVE r(nd_id, next, parent, leval) AS (

SELECT null AS nd_id, startnode AS next, 0 AS parent, 1 AS leval FROM vsm.run  -- WHERE startnode = 's1' -- параметр запроса
UNION ALL
SELECT t.nd_id AS nd_id, t.next AS next, leval AS parent, leval + 1 AS leval FROM vsm.next t JOIN r ON t.nd_id = r.next

) SELECT * FROM r) ORDER BY parent, leval

