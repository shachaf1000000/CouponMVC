import { Route, Switch, Redirect } from 'react-router-dom';

import { Fragment } from 'react';
import { useSelector } from 'react-redux';
import AdminMain from './components/AdminMain';
import CustomerMain from './components/CustomerMain';

import Header from './components/Header';
import Main from './components/Main';
import Auth from './login/Auth';


function App() {
  const role = useSelector(state => state.auth.role);

  return (
    <Fragment>
      <Header />

      <Switch>

        <Route path='/' exact>
          <Redirect to='/login' />

        </Route>

        <Route path='/login' exact>
          {role===null && <Auth/>}
          {role==="admin" && <AdminMain />}
          {role==="company" && <Main />}
          {role==="customer" && <CustomerMain/>}
        </Route>


      
      </Switch>

    </Fragment>
  );
}

export default App;