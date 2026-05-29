import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "./CoursesPage.css";

export default function CoursesPage() {
    const [courses, setCourses] = useState([]);

    useEffect(() => {
        fetch("http://localhost:5000/courses")
            .then(res => res.json())
            .then(setCourses);
    }, []);

    return (
        <main className="courses-page">
            <section className="courses-hero">
                <div>
                    <p className="courses-eyebrow">Course catalog</p>
                    <h1>Courses</h1>

                </div>
            </section>

            <section className="courses-grid" aria-label="Available courses">
                {courses.map(course => (
                    <article className="course-card" key={course.id}>
                        <div className="course-card-header">
                            <span className="course-badge">Course</span>
                            <span className="course-rating">
                                {course.avgRating.toFixed(1)}
                            </span>
                        </div>

                        <h2>{course.title}</h2>

                        <p className="course-teacher">{course.teacher}</p>

                        <div className="course-card-footer">
                            <Link className="course-link" to={`/courses/${course.id}`}>
                                Details
                            </Link>
                        </div>
                    </article>
                ))}
            </section>
        </main>
    );
}
