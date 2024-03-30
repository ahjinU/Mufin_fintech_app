import type { Meta, StoryObj } from '@storybook/react';

import BackButton from './BackButton';

const meta = {
  title: 'Common/BackButton',
  component: BackButton,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    label: {
      description: '버튼의 텍스트입니다.',
    },
  },
} satisfies Meta<typeof BackButton>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    label: '회원가입',
  },
};
