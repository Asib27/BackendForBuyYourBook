package com.asib27.authentication.Reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "select avg(rating) from reviews where book_id=?1", nativeQuery = true)
    double getAvgRatingByIsbn(String id);

    @Query(value = "select count (*) " +
            "from reviews where book_id=?1", nativeQuery = true)
    int getReviewCountByBookName(String id);

    @Query(value = "select T.rating,T.total*100/(select count(*) from reviews where book_id=?1) as percentage " +
            "from(select rating,count(*) as total " +
            "from reviews where book_id=?1 " +
            "group by rating order by rating)as T " +
            "group by T.rating,t.total", nativeQuery = true)
    List<Object[]> getRatingPercentage(String id);

    @Query(value = "select * from reviews where book_id=?1",nativeQuery = true)
    List<Review> getReviewByBookId(String book_id);

    @Query(value = "select * from reviews where book_id=?1 order by random() limit ?2", nativeQuery = true)
    List<Review> getRandomReviews(String id, int no);

    @Query(value = "(select * from reviews where book_id=?1 and rating >3 order by random() limit (?2)/2) union " +
            "(select * from reviews where book_id=?1 and rating <3 order by random() limit ((?2)-(?2)/2))", nativeQuery = true)
    List<Review>getMixedReviews(String id, int no);

    @Modifying
    @Query(value = "Call notification_upvote(?1)", nativeQuery = true)
    void updateUpvote(Long id);

    @Modifying
    @Query(value = "Call notification_downvote(?1)", nativeQuery = true)
    void updateDownvote(Long id);


}
