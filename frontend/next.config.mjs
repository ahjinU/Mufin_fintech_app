/** @type {import('next').NextConfig} */

import withPWAInit from '@ducanh2912/next-pwa';

const withPWA = withPWAInit({
  dest: 'public',
});

const nextConfig = {
  images: {
    remotePatterns: [
      {
        protocol: 'https',
        hostname: 'jasumin.s3.ap-northeast-2.amazonaws.com',
      },
    ],
  },
};

export default withPWA(nextConfig);
