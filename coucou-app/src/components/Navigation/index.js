import React from 'react';
import { BrowserRouter as Router, Route, Link as RouterLink } from 'react-router-dom';

import { Container, Typography, Box, Link } from '@material-ui/core';
import { AppBar, Toolbar, IconButton, Button } from '@material-ui/core';
import { Menu, MenuItem, ListItemIcon, ListItemText } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import MenuIcon from '@material-ui/icons/Menu';
import HomeOutlinedIcon from '@material-ui/icons/HomeOutlined';
import ExitToAppOutlinedIcon from '@material-ui/icons/ExitToAppOutlined';
import SettingsOutlinedIcon from '@material-ui/icons/SettingsOutlined';
import HelpOutlineOutlinedIcon from '@material-ui/icons/HelpOutlineOutlined';
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import CalendarTodayIcon from '@material-ui/icons/CalendarToday';

import Home from '../Home';
import Villages from '../VillagesHome';
import Conversations from '../ConversationsHome';
import Planner from '../PlannerHome';
import Journal from '../JournalHome';
import SignUp from '../SignUp';
import SignIn from '../SignIn';

import * as ROUTES from '../../constants/routes'

const useStyles = makeStyles(theme => ({
    root: {
        flexGrow: 1,
    },
    menuButton: {
        marginRight: theme.spacing(2),
    },
    title: {
        flexGrow: 1,
    },
    menu: {
        anchorOrigin: {
            vertical: 'bottom',
            horizontal: 'center',
        },
        transformOrigin: {
            vertical: 'top',
            horizontal: 'center',
        },
    },
    profileMenu: {
        anchorOrigin: {
            vertical: 'bottom',
            horizontal: 'center',
        },
        transformOrigin: {
            vertical: 'top',
            horizontal: 'center',
        },
    },
    appBar: {
        zIndex: theme.zIndex.drawer + 1
    },
    toolbar: theme.mixins.toolbar,
}));

export default function Navigation() {
  const classes = useStyles();

  const [anchorEl, setAnchorEl] = React.useState(null);
  const [profileAnchorEl, setProfileAnchorEl] = React.useState(null);

  const handleAppMenu = event => {
    setAnchorEl(event.currentTarget);
  };

  const handleProfileMenu = event => {
    setProfileAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
    setProfileAnchorEl(null);
  };

  const handleLogin = () => {
    window.location.href= '/signin';
  };

  const handleVillages = () => {
    window.location.href= '/villages';
  };

  const handleConversations = () => {
    window.location.href= '/conversations';
  };

  const handlePlanner = () => {
    window.location.href= '/planner';
  };

  const handleJournal = () => {
    window.location.href= '/journal';
  };

  return (
        <Router>
    <div className={classes.root}>
            <div className={classes.toolbar} />

            <Menu id="app-menu" anchorEl={anchorEl} keepMounted open={Boolean(anchorEl)} onClose={handleClose}
             className={classes.menu}>

                <MenuItem onClick={handleVillages}>
                    <ListItemIcon><HomeOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Villages" />
                </MenuItem>
                <MenuItem onClick={handleConversations}>
                    <ListItemText inset primary="Conversations" />
                </MenuItem>
                <MenuItem onClick={handlePlanner}>
                    <ListItemIcon><CalendarTodayIcon /></ListItemIcon>
                    <ListItemText primary="Planner" />
                </MenuItem>
                <MenuItem divider onClick={handleJournal}>
                    <ListItemText inset primary="Journal" />
                </MenuItem>
                <MenuItem>
                    <ListItemIcon><SettingsOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Settings" />
                </MenuItem>
                <MenuItem>
                    <ListItemIcon><HelpOutlineOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Help & Feedback" />
                </MenuItem>
            </Menu>

            <Menu id="profile-menu" anchorEl={profileAnchorEl} keepMounted open={Boolean(profileAnchorEl)} onClose={handleClose}
             className={classes.profileMenu}>

                <MenuItem onClick={handleLogin}>
                    <ListItemText inset primary="Login" />
                </MenuItem>
                <MenuItem divider>
                    <ListItemText inset primary="Profile" />
                </MenuItem>
                <MenuItem>
                    <ListItemIcon><ExitToAppOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Logout" />
                </MenuItem>
            </Menu>

            <AppBar position="fixed" className={classes.appBar}>
                <Toolbar>
                    <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu"
                        aria-controls="app-menu" aria-haspopup="true" onClick={handleAppMenu}>

                        <MenuIcon/>
                    </IconButton>
                    <Typography variant="h6" className={classes.title}>
                        Coucou
                    </Typography>
                    <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu"
                        aria-controls="profile-menu" aria-haspopup="true" onClick={handleProfileMenu}>

                        <AccountCircleIcon/>
                    </IconButton>
                </Toolbar>
            </AppBar>

            <Route exact path={ROUTES.LANDING} component={Home} />
            <Route path={ROUTES.VILLAGES} component={Villages} />
            <Route path={ROUTES.CONVERSATIONS} component={Conversations} />
            <Route path={ROUTES.PLANNER} component={Planner} />
            <Route path={ROUTES.JOURNAL} component={Journal} />
            <Route path={ROUTES.SIGN_UP} component={SignUp} />
            <Route path={ROUTES.SIGN_IN} component={SignIn} />
    </div>
        </Router>
  );
}
