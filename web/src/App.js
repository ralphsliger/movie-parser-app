import "./styles.css";
import { Provider } from "react-redux";

import { store } from "./store/store";

import Movies from "./components/Movies";

export default function App() {
  return (
    <div className="App">
      <Provider store={store}>
        <Movies />
      </Provider>
    </div>
  );
}
