import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import "./CourseDetailsPage.css";

export default function CourseDetailsPage() {
    const { id } = useParams();

    const [course, setCourse] = useState(null);

    const [review, setReview] = useState({
        rating: 5,
        comment: ""
    });

    const [enroll, setEnroll] = useState({
        phone: "",
        email: ""
    });

    const normalizeRating = value => {
        const rating = Number(value);

        if (Number.isNaN(rating)) {
            return 1;
        }

        return Math.min(5, Math.max(1, rating));
    };

    useEffect(() => {
        fetch(`http://localhost:5000/courses/${id}`)
            .then(res => res.json())
            .then(setCourse);
    }, [id]);

    const addReview = async () => {
        const res = await fetch(
            `http://localhost:5000/courses/${id}/reviews`,
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    ...review,
                    rating: normalizeRating(review.rating)
                })
            }
        );

        const newReview = await res.json();

        setCourse(prev => {
            const updatedReviews = [...prev.Reviews, newReview];
            const newAvgRating = updatedReviews.reduce((sum, r) => sum + r.rating, 0) / updatedReviews.length;

            return {
                ...prev,
                Reviews: updatedReviews,
                avgRating: newAvgRating
            };
        });

        setReview({ rating: 5, comment: "" });
    };

    const enrollCourse = async () => {
        await fetch(`http://localhost:5000/courses/${id}/enroll`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(enroll)
        });

        alert("Enrolled!");
    };

    if (!course) return <div className="course-details-loading">Loading...</div>;

    return (
        <main className="course-details-page">
            <section className="course-details-card course-details-header">
                <Link className="course-details-back" to="/">
                    Back to courses
                </Link>

                <h1>{course.title}</h1>

                <p className="course-details-description">{course.description}</p>

                <div className="course-details-meta">
                    <p>Teacher: {course.teacher}</p>
                    <p>Duration: {course.duration}</p>
                    <p>Rating: {course.avgRating.toFixed(1)}</p>
                </div>
            </section>

            <section className="course-details-card">
                <h2>Reviews</h2>

                <div className="reviews-list">
                    {course.Reviews.map(r => (
                        <article className="review-item" key={r.id}>
                            <b>{r.rating} / 5</b>
                            <p>{r.comment}</p>
                        </article>
                    ))}
                </div>
            </section>

            <section className="course-details-card">
                <h2>Add review</h2>

                <div className="course-form">
                    <label>
                        Rating
                        <input
                            type="number"
                            min="1"
                            max="5"
                            step="1"
                            value={review.rating}
                            onChange={e =>
                                setReview({
                                    ...review,
                                    rating: normalizeRating(e.target.value)
                                })
                            }
                        />
                    </label>

                    <label>
                        Comment
                        <textarea
                            value={review.comment}
                            onChange={e =>
                                setReview({
                                    ...review,
                                    comment: e.target.value
                                })
                            }
                        />
                    </label>

                    <button onClick={addReview}>
                        Send review
                    </button>
                </div>
            </section>

            <section className="course-details-card">
                <h2>Enroll</h2>

                <div className="course-form">
                    <label>
                        Phone
                        <input
                            placeholder="Phone"
                            value={enroll.phone}
                            onChange={e =>
                                setEnroll({
                                    ...enroll,
                                    phone: e.target.value
                                })
                            }
                        />
                    </label>

                    <label>
                        Email
                        <input
                            placeholder="Email"
                            value={enroll.email}
                            onChange={e =>
                                setEnroll({
                                    ...enroll,
                                    email: e.target.value
                                })
                            }
                        />
                    </label>

                    <button onClick={enrollCourse}>
                        Enroll
                    </button>
                </div>
            </section>
        </main>
    );
}