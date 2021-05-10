import React from 'react';
import { Container, Typography, Box, Link, TextField } from '@material-ui/core';
import { Card, CardContent } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles(theme => ({
    card: {
        minWidth: 250,
        maxWidth: 250,
        margin: 10,
    },
}));

export default function Village(props) {
  const classes = useStyles();

  return (
    <Card raised className={classes.card}>
        <CardContent>
            <Typography>{props.title}</Typography>
        </CardContent>
    </Card>
  );
}
