import React, { useState, useEffect, useCallback } from "react";

import MoviesList from "./MoviesList";
import CreateCoupon from "./CreateCoupon";
import './Main.module.css';
import { useDispatch, useSelector } from "react-redux";
import { authActions } from "../store/auth";
import CreateCompany from "./CreateCompany";
import CreateCustomer from "./CreateCustomer";
import ShowDetails from "./ShowDetails";

function AdminMain() {
  const token = useSelector(state => state.auth.token);
  const dispatch = useDispatch();
  const [coupons, setCoupons] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [content, setContent] = useState(<p>No items to show...</p>);
  const [error, setError] = useState(null);



  const getCompaniesHandler = useCallback(async () => {

    const requestOptions = {
      method: "GET",
      headers: { "Content-Type": "application/json" , token: token},
    };
    try {
      const response = await fetch("/admin/getCompanies", requestOptions);
      if (!response.ok) {
        window.alert("you're now about to gtfo :)");
        dispatch(authActions.logout());
        throw new Error("Something went wrong!");
      }
      console.log("no error :)");
      const data = await response.json();
      console.log(JSON.stringify(data));
      
      setContent(data.map(company => <ShowDetails type="Company" item={company}/>));

    } catch (error) {
      setError(error.message);
    }
    setIsLoading(false);
  }, []);

  const getCustomersHandler = useCallback(async () => {

    const requestOptions = {
      method: "GET",
      headers: { "Content-Type": "application/json" , token: token},
    };
    try {
      const response = await fetch("/admin/getCustomers", requestOptions);
      if (!response.ok) {
        window.alert("you're now about to gtfo :)");
        dispatch(authActions.logout());
        throw new Error("Something went wrong!");
      }
      
      console.log("no error :)");
      const data = await response.json();
      
      setContent(data.map(customer => <ShowDetails type="Customer" item={customer}/>));

    } catch (error) {
      setError(error.message);
    }
    setIsLoading(false);
  }, []);

  useEffect(() => {
    dispatch(authActions.setName("Admin"))
  }, []);

  return (
    <React.Fragment>
      <section><CreateCompany/></section>
      <section><CreateCustomer/></section>
      <section>
        <h1>Items list</h1>  
        <button onClick={getCompaniesHandler}>Get Companies</button>
        <button onClick={getCustomersHandler}>Get Customers</button>
        {content}
      </section>
    </React.Fragment>
  );
}

export default AdminMain;
