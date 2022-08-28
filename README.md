# BackendForBuyYourBook
Exposes api endpoints for BuyYourBook frontend

## Review service:
**Apis**: 
- `/api/book/{isbn}/review/all` 
    - returns all reviews of book.
- `/api/book/{isbn}/review/add` - add reviews to the book
- `/api/review/delete/{review_id}` - deletes reviews with review_id
- `/api/review/upvote/{review_id}` - upvotes review with review_id
- `/api/review/downvote/{review_id}` - downvotes review with review_id
- `/api/book/{isbn}/rating/average_rating` - return average rating
- `/api/book/{isbn}/rating/count` - return rating count
- `/api/book/{isbn}/rating/rating_percentage` - return rating wise percentage
- `/api/book/{isbn}/rating/stat` - returns average rating, rating count, rating wise percentage
- `/api/book/{isbn}/review?type=reviewType&count=no` - returns `count` no of `reviewType` reviews

## User service:
**Apis**:
- `/api/user/all` 
    - get 
    - get all users
- `/api/user/add` 
    - post
    - add user
- `/api/user/{userid}`
    - get
    - get user with id `userid`
- `/api/user/currentUser`
    - get
    - get current user
- `/api/user/{user_id}/follows/{id}`
    - post
    - user with id `user_id` follows `id` user
- `/api/user/{user_id}/unfollows/{id}`
    - post
    - user with id `user_id` unfollows `id` user
- `/api/user/{user_id}/edit`
    - post
    - edits user