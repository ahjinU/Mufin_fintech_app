import type { Config } from 'tailwindcss';

const config: Config = {
  content: [
    './src/pages/**/*.{js,ts,jsx,tsx,mdx}',
    './src/components/**/*.{js,ts,jsx,tsx,mdx}',
    './src/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      backgroundImage: {
        'gradient-radial': 'radial-gradient(var(--tw-gradient-stops))',
        'gradient-conic':
          'conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))',
      },
      colors: {
        'custom-white': '#ffffff',
        'custom-red': '#cd2626',
        'custom-blue': '#5969ff',
        'custom-light-sky': '#e4fbff',
        'custom-purple': '#7868e6',
        'custom-light-purple': '#b8b5ff',
        'custom-dark-purple': '#5242c0',
        'custom-black': '#27272a',
        'custom-light-gray': '#edeef7',
        'custom-dark-gray': '#52525b',
        'custom-medium-gray': '#d4d4d8',
        'custom-black-with-opacity': 'rgba(39, 39, 42, 0.8)',
        'custom-red-with-opacity': 'rgba(205, 38, 38, 0.2)',
        'custom-blue-with-opacity': 'rgba(89, 105, 255, 0.2)',
      },
    },
  },
  plugins: [],
};
export default config;
