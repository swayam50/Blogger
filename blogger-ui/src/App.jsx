import React from 'react';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import Page from './layouts/Page';
import Auth from './layouts/Auth';
import Login from './pages/Login';
import Register from './pages/Register';
import Home from './pages/Home';
import WritePost from './pages/WritePost';
import ReadPost from './pages/ReadPost';

import './App.scss';

const router = createBrowserRouter([
    {
        path: '/',
        element: <Page />,
        children: [
            {
                path: '/',
                element: <Home />
            },
            {
                path: '/post/:postId',
                element: <ReadPost />
            },
            {
                path: '/publish',
                element: <WritePost />
            }
        ]
    },
    {
        path: '/auth',
        element: <Auth />,
        children: [
            {
                path: '/auth/login',
                element: <Login />
            },
            {
                path: '/auth/register',
                element: <Register />
            }
        ]
    }
], { basename: '/blogger' });

const App = () => {
    return (
        <div className="app">
            <div className="app-container">
                <RouterProvider router={router} />
            </div>
        </div>
    );
};

export default App;
