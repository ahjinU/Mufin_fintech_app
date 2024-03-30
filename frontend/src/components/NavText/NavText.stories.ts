import type { Meta, StoryObj } from '@storybook/react';

import NavText from './NavText';

const meta = {
  title: 'Common/NavText',
  component: NavText,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    text: {
      description: '어느 페이지로 이동할 지 텍스트로 명시합니다.',
    },
    link: {
      description: '페이지의 라우트를 명시합니다.',
    },
  },
} satisfies Meta<typeof NavText>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    text: '메인 페이지로 가기',
    link: '/',
  },
};
