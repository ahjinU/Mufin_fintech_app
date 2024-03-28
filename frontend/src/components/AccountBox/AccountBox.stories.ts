import type { Meta, StoryObj } from '@storybook/react';

import AccountBox from './AccountBox';

const meta = {
  title: 'Common/AccountBox',
  component: AccountBox,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    text: {
      description: '안내 텍스트입니다.',
    },
  },
} satisfies Meta<typeof AccountBox>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    text: '부모님 먼저 가입한 후 아이도 회원가입할 수 있어요!',
    money: 12000,
  },
};
