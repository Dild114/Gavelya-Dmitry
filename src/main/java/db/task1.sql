SELECT COUNT(*) AS count_profiles_without_posts FROM profile
LEFT JOIN post on post.profile_id = profile.profile_id
WHERE post.profile_id IS NULL