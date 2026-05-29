import { BrowserRouter, Routes, Route } from "react-router-dom";

import CoursesPage from "./pages/CoursesPage";
import CourseDetailsPage from "./pages/CourseDetailsPage";

function App() {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<CoursesPage />} />

          <Route
              path="/courses/:id"
              element={<CourseDetailsPage />}
          />

        </Routes>
      </BrowserRouter>
  );
}

export default App;
