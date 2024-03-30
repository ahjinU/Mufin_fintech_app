import type { Meta, StoryObj } from '@storybook/react';

import TinyButton from './TinyButton';

const meta = {
  title: 'Common/TinyButton',
  component: TinyButton,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    label: {
      description: '작은 버튼의 텍스트입니다.',
    },
  },
} satisfies Meta<typeof TinyButton>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    label: '중복 확인',
  },
};
