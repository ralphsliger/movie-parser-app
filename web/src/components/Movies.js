import { useEffect } from "react";
import "../styles/Movies.css";

import { useSelector, useDispatch } from "react-redux";
import { getMovies } from "../actions/moviesAction";

import MoviesList from "./MoviesList";

export default function Movies() {
  const dispatch = useDispatch();
  const movies = useSelector((store) => store.movies);

  useEffect(() => {
    dispatch(getMovies());
  }, []);

  return (
    <div className="mainContainer">
      <MoviesList movies={movies} />
    </div>
  );
}
