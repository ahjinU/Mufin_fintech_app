import type { Meta, StoryObj } from '@storybook/react';

import SubGuideText from './SubGuideText';

const meta = {
  title: 'Common/SubGuideText',
  component: SubGuideText,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    text: {
      description: '연보라색 안내 텍스트입니다.',
    },
  },
} satisfies Meta<typeof SubGuideText>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    text: '적금 상품에 가입하려면 아래 약관 동의가 필요해요.',
  },
};
