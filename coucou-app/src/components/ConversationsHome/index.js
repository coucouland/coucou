import React from 'react';
import { Container, Typography, Box, Link } from '@material-ui/core';
import { AppBar, Toolbar, IconButton, Button } from '@material-ui/core';
import { Menu, MenuItem, Drawer, List, ListItem, ListItemIcon, ListItemText, Divider, ListSubheader } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import MenuIcon from '@material-ui/icons/Menu';
import HomeOutlinedIcon from '@material-ui/icons/HomeOutlined';
import ExitToAppOutlinedIcon from '@material-ui/icons/ExitToAppOutlined';
import SettingsOutlinedIcon from '@material-ui/icons/SettingsOutlined';
import StarBorderOutlinedIcon from '@material-ui/icons/StarBorderOutlined';
import TrendingUpOutlinedIcon from '@material-ui/icons/TrendingUpOutlined';
import WatchLaterOutlinedIcon from '@material-ui/icons/WatchLaterOutlined';

const useStyles = makeStyles(theme => ({
    drawer: {
        flexShrink: 0,
    },
    drawerPaper: {
    },
    toolbar: theme.mixins.toolbar,
}));

export default function Conversations() {
  const classes = useStyles();

  const [anchorEl, setAnchorEl] = React.useState(null);
  
  const handleClick = event => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <div>
        <Drawer variant="permanent" anchor="right" className={classes.drawer} classes={{ paper: classes.drawerPaper, }}>
            <div className={classes.toolbar} />
            <List>
                <ListItem button>
                    <ListItemIcon><WatchLaterOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Recent" />
                </ListItem>
                <ListItem button>
                    <ListItemIcon><StarBorderOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Starred" />
                </ListItem>
                <ListItem button>
                    <ListItemIcon><TrendingUpOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Popular" />
                </ListItem>
            </List>
            <Divider />
            <List
                subheader={
                    <ListSubheader>Village</ListSubheader>
                }>
                <ListItem button>
                    <ListItemText primary="Village 1" />
                </ListItem>
            </List>
        </Drawer>

        <Container maxWidth="sm">
          <Box my={4}>
            <Typography variant="h4" component="h1" gutterBottom>
              Coucou - Conversations
            </Typography>
          </Box>
        </Container>
    </div>
  );
}
