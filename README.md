# BackendForBuyYourBook
Exposes api endpoints for BuyYourBook frontend

## Review service:
**Base_url**: /api/book/{isbn}/review  

**`isbn`**: isbn of the book

**Apis**: 
- `/api/book/{isbn}/review/all` - returns all reviews of book.
- `/api/book/{isbn}/review/add` - add reviews to the book
- `/api/review/delete/{review_id}` - deletes reviews with review_id
- `/api/review/upvote/{review_id}` - upvotes review with review_id
- `/api/review/downvote/{review_id}` - downvotes review with review_id
- `/api/book/{isbn}/rating/average_rating` - return average rating
- `/api/book/{isbn}/rating/count` - return rating count
- `/api/book/{isbn}/rating/rating_percentage` - return rating wise percentage
- `/api/book/{isbn}/rating/stat` - returns average rating, rating count, rating wise percentage
- `/api/book/{isbn}/review?type=reviewType&count=no` - returns `count` no of `reviewType` reviews
