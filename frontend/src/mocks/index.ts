export async function initMocks() {
  const isServer = typeof window === 'undefined';

  if (isServer) {
    const { server } = await import('./server');
    server.listen();
  } else {
    const { worker } = await import('./browser');
    worker.start();
  }
}
