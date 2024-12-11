SELECT post.post_id FROM post
JOIN comment ON post.post_id = comment.post_id
GROUP BY post.post_id
HAVING COUNT(comment.comment_id) <= 1
ORDER BY post_id LIMIT 10
