import type { Meta, StoryObj } from '@storybook/react';

import NavButton from './NavButton';

const meta = {
  title: 'Common/NavButton',
  component: NavButton,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    mode: {
      description: 'HIGHLIGHT는 텍스트를 강조시킵니다.',
    },
    label: {
      description: '버튼은 텍스트입니다.',
    },
    link: {
      description: '버튼이 이동할 페이지 라우트입니다.',
    },
  },
} satisfies Meta<typeof NavButton>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Highlight: Story = {
  args: {
    mode: 'HIGHLIGHT',
    label: '쟈수민',
    link: '/',
  },
};

export const General: Story = {
  args: {
    mode: 'GENERAL',
    label: '최근 결제 내역 확인하기',
    link: '/',
  },
};
