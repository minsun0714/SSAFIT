/** @type {import('tailwindcss').Config} */
export default {
  content: ['./public/**/*.html', './src/**/*.{js,jsx,ts,tsx,vue}'],
  theme: {
    colors: {
      blue: {
        100: '#DEE8FF',
        200: '#AFC7FC',
        300: '#76A0FF',
        400: '#4880FF',
      },
      gray: {
        200: '#F2F2F2',
      },
      white: 'white',
    },
  },
  plugins: [],
}
