import React from 'react';
import { Container, Typography, Box, Link } from '@material-ui/core';

export default function Home() {

  return (
    <div>
        <Container maxWidth="sm">
          <Box my={4}>
            <Typography variant="h4" component="h1" gutterBottom>
              Coucou - a platform for sharing.
            </Typography>
          </Box>
        </Container>
    </div>
  );
}
