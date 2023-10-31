import React from 'react';

const ReadPost = () => {
    return (
        <div className="read-post">
            <div className="content">
                <img src="https://images.pexels.com/photos/1000366/pexels-photo-1000366.jpeg" alt="post-img" />
                <div className="user">
                    <img src="https://images.pexels.com/photos/1000366/pexels-photo-1000366.jpeg" alt="user-img" />
                    <div className="info">
                        <span>John Doe</span>
                        <p>Posted 2 days ago</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ReadPost;