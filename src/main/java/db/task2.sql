SELECT post.post_id FROM post
JOIN comment ON post.post_id = comment.post_id
WHERE post.title ~ '^[0-9]' and LENGTH(post.content) > 20
GROUP BY post.post_id
HAVING COUNT(comment.comment_id) = 2
ORDER BY post_id