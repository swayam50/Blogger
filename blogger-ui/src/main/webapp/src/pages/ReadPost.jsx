import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';
import Menu from '../components/Menu';

const ReadPost = () => {
    return (
        <div className="read-post">
            <div className="post">
                <img className="banner" src="https://images.pexels.com/photos/1000366/pexels-photo-1000366.jpeg" alt="post-img" />
                <div className="user">
                    <img src="https://images.pexels.com/photos/1000366/pexels-photo-1000366.jpeg" alt="user-img" />
                    <div className="info">
                        <span>John Doe</span>
                        <p>Posted 2 days ago</p>
                    </div>
                    <div className="action">
                        <Link to={`/publish?edit=2`}>
                            <FontAwesomeIcon className="icon" icon="fa-solid fa-pen-to-square" style={{ color: "#00563b" }} />
                        </Link>
                        <Link to={`/home`}>
                            <FontAwesomeIcon className="icon" icon="fa-solid fa-trash" style={{ color: "#e81717" }} />
                        </Link>
                    </div>
                </div>
                <h1 className="title">This is a very cool looking website</h1>
                <p className="content">Lorem ipsum dolor sit amet consectetur adipisicing elit. Nobis facilis corrupti possimus iure laboriosam saepe aliquid eum debitis dolorem, perferendis suscipit ducimus. Et, consequuntur eaque! Nihil officiis illo accusamus reprehenderit architecto ut praesentium quod vel quibusdam minima totam repellat delectus consectetur atque nesciunt, qui voluptatibus dignissimos voluptates impedit? Magni quam qui quod necessitatibus quas unde sapiente recusandae! Rem vero inventore hic minima dolor. Voluptatum debitis facere assumenda sed et maiores numquam veniam facilis rerum architecto, tenetur porro praesentium. Explicabo, dolorem illo animi nisi ut error quos voluptates cupiditate, suscipit expedita quaerat officiis. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Officiis sapiente nisi blanditiis similique expedita, totam veritatis aspernatur ex dolor cumque quasi ut sunt suscipit odit iusto earum labore, illo molestias hic, voluptatem a consequatur iste voluptatibus? Sequi similique sed placeat! Neque libero ut quis veniam beatae eveniet, perferendis vel consequuntur eius repellat natus fuga qui illum!
                </p>
            </div>
            <Menu />
        </div>
    );
};

export default ReadPost;