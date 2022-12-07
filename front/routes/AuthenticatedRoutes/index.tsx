import { Routes, Route } from 'react-router-dom'
// import { NavBar } from '../../components/NavBar'
// import { CategoryFormPage } from '../../pages/CategoryFormPage'
import Menu from '../../pages/menu/index'
import Home from '../../pages/index'
import Wallet from './../../pages/wallet/index';

export function AuthenticatedRoutes() {
    return (
        <>
            {/* <NavBar /> */}
            <Routes>
                <Route path="/" element={<Home />} />

                <Route path="/menu" element={<Menu />} />
                {/* <Route path="/categories/new" element={<Menu />} /> */}
                <Route path="/menu/:id" element={<Menu />} />

                <Route path="/wallet" element={<Wallet />} />
                {/* <Route path="/products/new" element={<Wallet />} /> */}
                <Route path="/wallet/:id" element={<Wallet />} />

                <Route path="*" element={<Home />} />
            </Routes>
        </>
    )
}